package com.example.linknu.repository;

import com.example.linknu.Entity.TaxiParty;
import com.example.linknu.dto.TaxiPartyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaxiPartyRepository extends JpaRepository<TaxiParty,Long> {
    //@Query("SELECT u FROM User u INNER JOIN u.address a WHERE a.city = :city")
    //@Query("SELECT TP , TPU.board_member_count AS number_of_enrolled FROM linknu.taxi_party TP LEFT JOIN (SELECT board_id, COUNT(*) AS board_member_count FROM linknu.taxi_party_user GROUP BY board_id) TPU ON TP.id = TPU.board_id")
    //List<TaxiPartyDto> findJoin();
}
