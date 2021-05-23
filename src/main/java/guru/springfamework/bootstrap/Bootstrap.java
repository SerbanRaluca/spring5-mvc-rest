package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    private CustomerRepository customerRepository;

    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();

        loadVendors();
    }

    private void loadCustomers() {
        Customer alice = new Customer();
        alice.setFirstname("Alice");
        alice.setLastname("Eastman");

        Customer sam = new Customer();
        sam.setFirstname("Sam");
        sam.setLastname("Axe");

        Customer freddy = new Customer();
        freddy.setFirstname("Freddy");
        freddy.setLastname("Meyers");

        Customer freddie = new Customer();
        freddie.setFirstname("Freddie");
        freddie.setLastname("Mercury");

        Customer joe = new Customer();
        joe.setFirstname("Joe");
        joe.setLastname("Buck");

        Customer michael = new Customer();
        michael.setFirstname("Micheal");
        michael.setLastname("Weston");

        customerRepository.save(alice);
        customerRepository.save(sam);
        customerRepository.save(freddie);
        customerRepository.save(freddy);
        customerRepository.save(joe);
        customerRepository.save(michael);

        System.out.println("Customers Loaded = " + customerRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Loaded = " + categoryRepository.count());
    }

    void loadVendors() {

        Vendor v1 = new Vendor();
        v1.setName("Western Tasty Fruits Ltd.");

        Vendor v2 = new Vendor();
        v2.setName("Exotic Fruits Company");

        Vendor v3 = new Vendor();
        v3.setName("Home Fruits");

        Vendor v4 = new Vendor();
        v4.setName("Fun Fresh Fruits Ltd.");

        Vendor v5 = new Vendor();
        v5.setName("Nuts for Nuts Company");

        vendorRepository.save(v1);
        vendorRepository.save(v2);
        vendorRepository.save(v3);
        vendorRepository.save(v4);
        vendorRepository.save(v5);

        System.out.println("Vendors Loaded = " + vendorRepository.count());
    }
}
