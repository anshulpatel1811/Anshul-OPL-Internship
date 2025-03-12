package com.harryPotter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.harryPotter.domain.HarryPotter;

import jakarta.transaction.Transactional;

public interface HarryPotterRepo extends JpaRepository<HarryPotter, Long>{

	//	List<HarryPotter> findByHouse(String house);
	
	//	List<HarryPotter> findByLocationAndHouse(String location,String house);

	List<HarryPotter> findBySpellContaining(String keywords);
	
	//	List<HarryPotter> findByLocationOrHouse(String location,String house);
	
	//String deleteByCharacte_name(String name);
	
	//List<HarryPotter> findByLiveTrue();
	
	//@Query(value = "SELECT s FROM HarryPotter s WHERE s.house=:h")
	@Query(value = "SELECT * FROM harry_potter_tbl WHERE house=?",nativeQuery = true)
	List<HarryPotter> findhouse(/*@Param("h")*/ String house);
	
	//@Query(value = "SELECT s FROM HarryPotter s WHERE s.location=:l AND s.house=:h")
	@Query(value = "SELECT * FROM harry_potter_tbl WHERE location=? AND house=?",nativeQuery = true)
	List<HarryPotter> findLocationAndhouse(/*@Param("l") */ String location,/*@Param("h")*/ String house);
	
	//@Query(value = "SELECT s FROM HarryPotter s WHERE s.location=:l OR s.house=:h")
	@Query(value = "SELECT * FROM harry_potter_tbl WHERE location=? OR house=?",nativeQuery = true)
	List<HarryPotter> findlocationOrhouse(@Param("l")  String location,@Param("h") String house);
	
	@Modifying
	//@Transactional
	@Query(value = "INSERT INTO harry_potter_tbl(character_name,location) VALUES('Deep','Maninagar')",nativeQuery = true)
	void saveData();
	
	@Modifying
	@Transactional
	//@Query(value = "DELETE FROM HarryPotter s WHERE s.characterName=:h")
	@Query(value = "DELETE FROM harry_potter_tbl WHERE character_name=?",nativeQuery = true)
	void deleteDatacharactername(/*@Param("h")*/ String character);
	
	@Modifying
	@Transactional
	//@Query(value = "UPDATE HarryPotter u SET u.characterName = :status WHERE u.id = :id")
	@Query(value = "UPDATE harry_potter_tbl SET character_name = ?2 WHERE id = ?1 ",nativeQuery = true)
	void updateData(/*@Param("id")*/ Long id, /*@Param("status")*/ String characterName);
	
//	@Query(value = "SELECT s.characterName ,s.house FROM HarryPotter s WHERE s.id=:h")
//	HarryPotter getData(@Param("h") Long id);
//	
//	@Query(value = "SELECT new com.harryPotter.domain.HarryPotterProxy(h.characterName, h.house) FROM HarryPotter h WHERE h.id = :id")
//	HarryPotter getData2(@Param("h") Long id);
	
}
