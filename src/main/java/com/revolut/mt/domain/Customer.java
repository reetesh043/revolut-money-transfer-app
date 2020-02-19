package com.revolut.mt.domain;

import java.util.Set;

/**
 * Entity class for users.
 */

public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Set<String> accounts;

    public Customer(){}

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param address
     * @param accounts
     */
    public Customer(Long id, String firstName, String lastName, String address, Set<String> accounts) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.accounts = accounts;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the accounts
     */
    public Set<String> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(Set<String> accounts) {
        this.accounts = accounts;
    }
}