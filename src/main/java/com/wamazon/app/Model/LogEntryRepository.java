package com.wamazon.app.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntryModel, Long> {
	List<LogEntryModel> findByUser(UserModel user);
}
