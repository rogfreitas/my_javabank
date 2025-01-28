package io.codeforall.bootcamp.javabank.persistence.daos.hibernate;

import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;

import io.codeforall.bootcamp.javabank.model.Customer;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class HibernateCustomerDao implements CustomerDao {

    private EntityManager em;
    private HibernateAccountDao accountDao;

    public void setAccountDAO(HibernateAccountDao HibernateAccountDao) {
        this.accountDao = HibernateAccountDao;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Customer findById(Integer id) {
        return em.find(Customer.class, id);
    }


    @Override
    public List<Customer> findAll() {


        CriteriaBuilder builder = em.getCriteriaBuilder();

        // 2 - create a new CriteriaQuery instance for the Customer entity
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);

        // 3 - get the root of the query, from where all navigation starts
        Root<Customer> root = criteriaQuery.from(Customer.class);

        // 4 - specify the item that is to be returned in the query result
        criteriaQuery.select(root);
        return em.createQuery(criteriaQuery).getResultList();
    }


    private Integer update(Customer customer) {

        Customer customerBD=em.merge(customer);

        return customerBD.getId();

    }

    private Integer insert(Customer customer)  {
        em.persist(customer);
        return customer.getId();
    }

    @Override
    public List<Integer> getCustomerIds() {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        // 2 - create a new CriteriaQuery instance for the Customer entity
        CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);

        // 3 - get the root of the query, from where all navigation starts
        Root<Customer> root = criteriaQuery.from(Customer.class);

        // 4 - specify the item that is to be returned in the query result
        criteriaQuery.select(root.get("id"));
        return em.createQuery(criteriaQuery).getResultList();
    }


    @Override
    public Customer saveOrUpdate(Customer modelObject) {



            Integer id = null;

            if (findById(modelObject.getId()) != null) {
                id = update(modelObject);
            } else {
                id = insert(modelObject);
            }

            modelObject.setId(id);
            return modelObject;
    }

    @Override
    public void delete(Integer id) {
       em.remove(findById(id));
    }
}
