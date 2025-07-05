package com.myproject.backend.cart;

import com.myproject.backend.userinfo.UserInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserInfo(UserInfo userInfo);

    List<ShoppingCart> findAllByUserInfo(UserInfo userInfo);

    List<ShoppingCart> findAllByUserInfo(UserInfo userInfo, Pageable p);

}
