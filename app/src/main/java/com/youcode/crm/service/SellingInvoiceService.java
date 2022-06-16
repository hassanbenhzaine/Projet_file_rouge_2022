package com.youcode.crm.service;

import com.youcode.crm.entity.SellingInvoice;
import com.youcode.crm.entity.dto.SellingInvoiceDTO;
import com.youcode.crm.mapper.SellingInvoiceMapper;
import com.youcode.crm.repository.SellingInvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SellingInvoiceService {

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final SellingInvoiceRepository sellingInvoiceRepository;
    private final SellingInvoiceMapper sellingInvoiceMapper;

    /**
     * The method is to retrieve all selling invoices from the database and display them.
     *
     * After downloading all the data about the selling invoice,
     * the data is mapped to dto which will display only those needed
     * @return list of all selling invoices with specification of data in SellingInvoiceToDTO
     */
    @Transactional(readOnly = true)
    public List<SellingInvoiceDTO> getAllSellingInvoices(Pageable pageable){
        return sellingInvoiceRepository.findAll(pageable)
                .stream()
                .map(sellingInvoiceMapper::mapSellingInvoiceToDTO)
                .collect(Collectors.toList());
    }

    /**
     * The method is to download a specific invoice from the database and display it.
     * After downloading all the data about the invoice,
     * the data is mapped to dto which will display only those needed
     *
     * @param id id of the invoice to be searched for
     * @throws ResponseStatusException if the id of the invoice you are looking for does not exist throws 404 status
     * @return detailed data about a specific invoice
     */
    @Transactional(readOnly = true)
    public SellingInvoiceDTO getInvoiceById(Long id) {
        SellingInvoice sellingInvoice = sellingInvoiceRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Selling invoice cannot be found, the specified id does not exist"));
        return sellingInvoiceMapper.mapSellingInvoiceToDTO(sellingInvoice);
    }

    /**
     * The task of the method is to add a invoice to the database.
     * @param sellingInvoice requestbody of the invoice to be saved
     * @return saving the invoice to the database
     */
    public SellingInvoiceDTO addNewInvoice(SellingInvoiceDTO sellingInvoice) {
        SellingInvoice addedSellingInvoice = sellingInvoiceRepository.save(sellingInvoiceMapper.mapSellingInvoiceDtoToSellingInvoice(sellingInvoice));
        return sellingInvoiceMapper.mapSellingInvoiceToDTO(addedSellingInvoice);
    }

    /**
     * Method deletes the selected invoice by id
     * @param id id of the invoice to be deleted
     * @throws ResponseStatusException if id of the invoice is incorrect throws 404 status with message
     */
    public void deleteInvoiceById(Long id) {
        try{
            sellingInvoiceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The specified id does not exist");
        }
    }

    @Transactional
    public SellingInvoiceDTO editSellingInvoice(SellingInvoiceDTO sellingInvoice) {
        SellingInvoice mappedSellingInvoice = sellingInvoiceMapper.mapSellingInvoiceDtoToSellingInvoice(sellingInvoice);
        SellingInvoice editedSellingInvoice = sellingInvoiceRepository.findById(mappedSellingInvoice.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice does not exist"));

        editedSellingInvoice.setInvoiceDate(mappedSellingInvoice.getInvoiceDate());
        editedSellingInvoice.setGrossValue(mappedSellingInvoice.getGrossValue());
        editedSellingInvoice.setTaxRate(mappedSellingInvoice.getTaxRate());
        editedSellingInvoice.setNetWorth(mappedSellingInvoice.getNetWorth());
        editedSellingInvoice.setCurrency(mappedSellingInvoice.getCurrency());
        editedSellingInvoice.setCustomer(mappedSellingInvoice.getCustomer());

        return sellingInvoiceMapper.mapSellingInvoiceToDTO(mappedSellingInvoice);
    }

    public List<SellingInvoice> addNewInvoices(Iterable<SellingInvoice> sellingInvoices) {
        return sellingInvoiceRepository.saveAll(sellingInvoices);
    }
}
