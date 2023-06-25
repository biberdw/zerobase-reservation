package com.zerobase.reservation.repository.review;

import com.zerobase.reservation.domain.reservation.Reservation;
import com.zerobase.reservation.domain.review.Review;
import com.zerobase.reservation.domain.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Modifying(clearAutomatically = true)
    @Query("delete from Review r where r.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);

    boolean existsByReservation(Reservation reservation);


    List<Review> findAllByShop(Shop shop);
}
