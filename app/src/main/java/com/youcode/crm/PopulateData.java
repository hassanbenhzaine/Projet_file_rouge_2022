package com.youcode.crm;

import com.youcode.crm.entity.*;
import com.youcode.crm.enums.CURRENCY;
import com.youcode.crm.enums.MODE_OF_TRANSPORT_CODE;
import com.youcode.crm.enums.UNIT_OF_MEASURE;
import com.youcode.crm.enums.USER_ROLE;
import com.youcode.crm.repository.*;
import com.youcode.crm.security.registration.token.ConfirmationToken;
import com.youcode.crm.security.registration.token.ConfirmationTokenRepository;
import com.youcode.crm.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class PopulateData implements CommandLineRunner{
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final SupplierService supplierService;
    private final TransportTypeRepository transportTypeRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductService productService;
    private final SellingInvoiceService sellingInvoiceService;
    private final PostRepository postRepository;
    private final AbsenteeismService absenteeismService;
    private final CommentRepository commentRepository;
    private final AbsenteeismReasonRepository absenteeismReasonRepository;
    private final ProductUnitService productUnitService;
    private final PurchaseService purchaseService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final PurchasePositionRepository purchasePositionRepository;

    @Override
    public void run(String... args) {
        Random random = new Random();

//        DEPARTEMENTS
        List<Department> departmentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Department department = Department.builder()
                    .name("department" + i)
                    .city("city" + i)
                    .name("departement" + i)
                    .build();
            departmentList.add(department);
        }
        departmentService.addNewDepartments(departmentList);

//        CUSTOMERS
       List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer customer = Customer.builder()
                    .city("city" + i)
                    .firstName("firstName" + i)
                    .lastName("lastName" + i)
                    .cin("AD" + i)
                    .zipCode("zipCode" + i)
                    .build();
            customerList.add(customer);
        }
        customerService.addNewCustomers(customerList);

//        EMPLOYES
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee employee = Employee.builder()
                    .firstName("firstName" + i)
                    .lastName("lastName" + i)
                    .birthdate(LocalDate.now().minusYears(1))
                    .department(departmentList.get(new Random().nextInt(departmentList.size())))
                    .email("example" + i + "@domain.com")
                    .cin("AD" + i)
                    .phone("067897846" + i)
                    .isEnabled(true)
                    .isLocked(false)
                    .password(passwordEncoder.encode("password"))
                    .salary(new Random().nextDouble() * 1000)
                    .gender("M")
                    .userRole(USER_ROLE.values()[random.nextInt(USER_ROLE.values().length)])
                    .build();

            employeeList.add(employee);
        }
        employeeService.addNewEmployes(employeeList);

//        TYPES OF TRANSPORT
        List<TransportType> transportTypeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TransportType transportType = TransportType.builder()
                    .code(MODE_OF_TRANSPORT_CODE.values()[random.nextInt(MODE_OF_TRANSPORT_CODE.values().length)])
                    .fullName("transportType" + i)
                    .maxLength(new Random().nextDouble())
                    .minLength(new Random().nextDouble())
                    .maxWeight(new Random().nextDouble())
                    .minWeight(new Random().nextDouble())
                    .transportCapacity(new Random().nextInt())
                    .build();

            transportTypeList.add(transportType);
        }
        transportTypeRepository.saveAll(transportTypeList);

//        SUPPLIERS
        List<Supplier> supplierList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Supplier supplier = Supplier.builder()
                    .activityStatus("activityStatus" + i)
                    .name("supplier" + i)
                    .modeOfTransportCode(transportTypeList.get(new Random().nextInt(transportTypeList.size())))
                    .build();

            supplierList.add(supplier);
        }
        supplierService.addNewSuppliers(supplierList);

//        PRODUCT TYPES
        List<ProductType> productTypeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductType productType = ProductType.builder()
                    .discount(new Random().nextDouble() * 100)
                    .fullName("fullName" + i)
                    .periodOfAvailability('C')
                    .build();

            productTypeList.add(productType);
        }
        productTypeRepository.saveAll(productTypeList);

//        PRODUCTS
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .name("product" + i)
                    .purchasePrice(new Random().nextDouble())
                    .sellingPrice(new Random().nextDouble())
                    .taxRate(new Random().nextDouble() * 100)
                    .unitOfMeasure(UNIT_OF_MEASURE.values()[random.nextInt(UNIT_OF_MEASURE.values().length)])
                    .type(productTypeList.get(new Random().nextInt(productTypeList.size())))
                    .build();

            productList.add(product);
        }
        productService.addNewProducts(productList);

//        Absenteeism REASONS
        List<AbsenteeismReason> absenteeismReasonList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AbsenteeismReason absenteeismReason = AbsenteeismReason.builder()
                    .comments("comment" + i)
                    .consent(new Random().nextBoolean())
                    .name("name" + i)
                    .build();
            absenteeismReasonList.add(absenteeismReason);
        }
        absenteeismReasonRepository.saveAll(absenteeismReasonList);

//        ABSENTEEISMS
        List<Absenteeism> absenteeismList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Absenteeism absenteeisms = Absenteeism.builder()
                    .dateFrom(LocalDate.now())
                    .dateTo(LocalDate.now())
                    .employee(employeeList.get(new Random().nextInt(employeeList.size())))
                    .reasonOfAbsenteeismCode(absenteeismReasonList.get(new Random().nextInt(absenteeismReasonList.size())))
                    .build();

            absenteeismList.add(absenteeisms);
        }
        absenteeismService.addNewAbsenteeisms(absenteeismList);

//        POSTS
        List<Post> postList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Post post = Post.builder()
                    .author(employeeList.get(new Random().nextInt(employeeList.size())))
                    .content("content" + i)
                    .createdAt(LocalDateTime.now())
                    .title("title" + i)
                    .build();

            postList.add(post);
        }
        postRepository.saveAll(postList);

        //        COMMENTS
        List<Comment> commentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Comment comment = Comment.builder()
                    .content("content" + i)
                    .post(postList.get(new Random().nextInt(postList.size())))
                    .createdAt(LocalDateTime.now())
                    .author(employeeList.get(new Random().nextInt(employeeList.size())))
                    .build();

            commentList.add(comment);
        }
        commentRepository.saveAll(commentList);

//        PRODUCT UNITS
        List<ProductUnit> productUnitList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductUnit productUnit = ProductUnit.builder()
                    .product(productList.get(new Random().nextInt(productList.size())))
                    .alternativeUnitOfMeasure("KG")
                    .conversionFactor(new Random().nextDouble())
                    .unitOfMeasure(UNIT_OF_MEASURE.values()[new Random().nextInt(UNIT_OF_MEASURE.values().length)])
                    .build();

            productUnitList.add(productUnit);
        }
        productUnitService.addNewProductUnits(productUnitList);


        //        PURCHASE POSITIONS
        List<PurchasePosition> purchasePositionList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PurchasePosition purchasePosition = PurchasePosition.builder()
                    .amount(new Random().nextDouble() * 1000)
                    .product(productList.get(new Random().nextInt(productList.size())))
                    .reclamationExist(new Random().nextBoolean())
                    .build();

            purchasePositionList.add(purchasePosition);
        }
        purchasePositionRepository.saveAll(purchasePositionList);

//        PURCHASES
        List<Purchase> purchaseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Purchase purchase = Purchase.builder()
                    .customer(customerList.get(new Random().nextInt(customerList.size())))
                    .purchaseDate(LocalDate.of(
                            new Random().nextInt(2019,2022),
                            new Random().nextInt(1,12),
                            new Random().nextInt(1,28)
                    ))

                    .invoiceExist(new Random().nextBoolean())
                    .receiptExist(new Random().nextBoolean())
                    .purchasePosition(purchasePositionList.get(i))
                    .build();

            purchaseList.add(purchase);
        }
        purchaseService.addNewPurchases(purchaseList);

        //        SELLING INVOICES
        List<SellingInvoice> invoiceList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            SellingInvoice sellingInvoice = SellingInvoice.builder()
                    .currency(CURRENCY.values()[random.nextInt(CURRENCY.values().length)])
                    .customer(customerList.get(new Random().nextInt(customerList.size())))
                    .grossValue(new Random().nextDouble())
                    .invoiceDate(LocalDate.of(
                            new Random().nextInt(2019,2022),
                            new Random().nextInt(1,12),
                            new Random().nextInt(1,28)
                    ))
                    .netWorth(new Random().nextDouble(0, 9000))
                    .taxRate(new Random().nextDouble() * 100)
                    .purchase(purchaseList.get(i))
                    .build();

            invoiceList.add(sellingInvoice);
        }
        sellingInvoiceService.addNewInvoices(invoiceList);

//        CONFIRMATION TOKEN
        List<ConfirmationToken> confirmationTokenList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ConfirmationToken confirmationToken = ConfirmationToken.builder()
                    .expiresAt(LocalDateTime.now().plusDays(15))
                    .token(UUID.randomUUID())
                    .confirmedAt(LocalDateTime.now())
                    .employee(employeeList.get(i))
                    .createdAt(LocalDateTime.now().minusDays(1))
                    .build();

            confirmationTokenList.add(confirmationToken);
        }
        confirmationTokenRepository.saveAll(confirmationTokenList);

        System.out.println("-------------- FINISHED POPULATING DATA --------------");
    }
}
