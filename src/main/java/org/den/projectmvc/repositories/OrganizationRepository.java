package org.den.projectmvc.repositories;
import org.den.projectmvc.models.Organization;
import org.den.projectmvc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {


}


