package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.Applications;

public interface ApplicationsRepository  extends JpaRepository<Applications, Long>{

	public List<Applications> findByUserIdAndIsActive(Long userId,Boolean isActive);
	
	public Applications findByIdAndIsActive(Long id,Boolean isActive);
	
	@Query(value = "SELECT lead_reference_no FROM lams.applications where application_type_id =:applicationType order by id desc limit 1",nativeQuery = true)
	public String getLastLeadReferenceNo(@Param("applicationType") Long applicationType);
	
	@Query(value = "SELECT distinct(app.userId) from Applications app where app.applicationTypeId.id in (:id) and app.isActive = true")
	public List<Long> getUserIdByApplicationTypeId(@Param("id")List<Long> appTypeId);
	
	public List<Applications> findByUserIdAndIsActiveAndApplicationTypeIdIdIn(Long userId,Boolean isActive,List<Long> applicationTypeIds);
}
