package guru.springframework.api.v1.mapper;

import guru.springframework.domain.Customer;
import guru.springframework.model.CustomerDTO;
import junit.framework.TestCase;
import org.junit.Test;

public class CustomerMapperTest extends TestCase {

    public static final long ID = 1L;
    public static final String FIRSTNAME = "Micheal";
    public static final String LASTNAME = "Weston";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void testCustomerToCustomerDTO() {

        //given
        Customer customer = new Customer();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);
        customer.setId(ID);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }
}