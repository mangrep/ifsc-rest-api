package in.co.techm.repository;

import in.co.techm.model.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends MongoRepository<Bank, Serializable> {

    Bank findByBranchIgnoreCaseAndBankIgnoreCase(String branch, String bank);

    Optional<List<Bank>> findByBankIgnoreCase(String bank);

    Optional<List<Bank>> findByBranchIgnoreCase(String branch);

    Optional<Bank> findByIfsc(String ifsc);

    Optional<Bank> findByMicrcode(String micrcode);

    Optional<List<Bank>> findByDistrictIgnoreCase(String district);

    Optional<List<Bank>> findByStateIgnoreCase(String district);

    @Query("{ 'branch':{$regex:?1,$options:'i'}, 'bank':{$regex:?0, $options: 'i'}}")
    Optional<List<Bank>> findByBankIgnoreCaseAndBranch(String bank, String branch);

    @Override
    @RestResource(exported = false)
    void delete(Bank bank);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void delete(Serializable serializable);

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Bank> iterable);

    @Override
    @RestResource(exported = false)
    <S extends Bank> List<S> save(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    <S extends Bank> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends Bank> S insert(S s);

    @Override
    @RestResource(exported = false)
    <S extends Bank> List<S> insert(Iterable<S> iterable);


}



