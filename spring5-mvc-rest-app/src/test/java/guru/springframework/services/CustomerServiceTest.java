package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.domain.Customer;
import guru.springframework.model.CustomerDTO;
import guru.springframework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    public static final long ID = 1L;
    public static final String FIRSTNAME = "Micheal";
    public static final String LASTNAME = "Weston";
    public static final String CUSTOMER_URL = "/api/v1/customers/1";

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void testGetAllCustomers() {

        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("firstname1");
        customer1.setLastname("lastname1");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("firstname2");
        customer2.setLastname("lastname2");
        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(2, customerDTOS.size());


    }

    @Test
    public void testGetCustomerById() {

        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        assertEquals("Michale", customerDTO.getFirstname());
    }

    @Test
    public void testCreateNewCustomer() {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customers/1", savedDto.getCustomerUrl());


    }

    @Test
    public void saveCustomerByDTO(){

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customers/1", savedDto.getCustomerUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {

        Long id = 1L;

        customerService.deleteCustomerById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}