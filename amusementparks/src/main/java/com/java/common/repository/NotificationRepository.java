package com.java.common.repository;

import com.java.common.entity.Notifications;
import com.java.enums.CommonEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Integer> {

    Page<Notifications> findAllByStatusOrderByPublicationStartDesc(CommonEnum.StatusEnum status, Pageable pageable);


    @Query(value = "SELECT * \n" +
            "FROM `t_notifications` \n " +
            "WHERE `t_notifications`.`title` LIKE %?1% \n" +
            "AND t_notifications.`status` = 0 " +
            "AND (t_notifications.publication_start BETWEEN ?2 AND ?3 OR (?2 = '' AND ?3 = '') ) \n" +
            "AND ( (t_notifications.publication_end BETWEEN ?4 AND ?5 OR (?4 = '' AND ?5 = '') ) OR t_notifications.publication_end is null) \n", nativeQuery = true)
    Page<Notifications> findAllByPosting(String title, String fromPublicationStart, String toPublicationStart, String toPublicationEnd, String fromPublicationEnd,Pageable pageable);

    @Query(value = "SELECT * \n" +
            "FROM `t_notifications` \n " +
            "WHERE `t_notifications`.`title` LIKE %?1% \n" +
            "AND t_notifications.`status` = 0 " +
            "AND (t_notifications.publication_start BETWEEN ?2 AND ?3 OR (?2 = '' AND ?3 = '') ) \n" +
            "AND ( (t_notifications.publication_end BETWEEN ?4 AND ?5 OR (?4 = '' AND ?5 = '') ) OR t_notifications.publication_end is null) \n" +
            "AND t_notifications.publication_start > now()", nativeQuery = true)
    Page<Notifications> findAllBeforePosting(String title, String fromPublicationStart, String toPublicationStart, String toPublicationEnd, String fromPublicationEnd,Pageable pageable);


    Page<Notifications> findAllByStatusAndTitleContainingOderBYPublicationStartDesc(CommonEnum.StatusEnum status, Pageable pageable);

    @Query(value = "SELECT * \n" +
            "FROM `t_notifications` \n " +
            "WHERE `t_notifications`.`title` LIKE %?1% \n" +
            "AND t_notifications.`status` = 0 " +
            "AND (t_notifications.publication_start BETWEEN ?2 AND ?3 OR (?2 = '' AND ?3 = '') ) \n" +
            "AND ( (t_notifications.publication_end BETWEEN ?4 AND ?5 OR (?4 = '' AND ?5 = '') ) OR t_notifications.publication_end is null) \n" +
            "AND t_notifications.publication_start <now()", nativeQuery = true)
    Page<Notifications> findAllAfterPosting(String title, String fromPublicationStart, String toPublicationStart, String toPublicationEnd, String fromPublicationEnd,Pageable pageable);
}
