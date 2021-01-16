package com.sam.takenote.repository;

import com.sam.takenote.entity.Shelf;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepository extends CrudRepository<Shelf, Integer> {

    @Query("select count(*) from Shelf s where s.shelfName = :shelfName and s.users.userName = :userName")
    Long countByShelfNameAndUserName(@Param("shelfName") String shelfName, @Param("userName") String userName);

    @Query("select s from Shelf s where s.shelfName = :shelfName and s.users.userName = :userName")
    Shelf findByNameAndUserName(@Param("shelfName") String shelfName, @Param("userName") String userName);

}
