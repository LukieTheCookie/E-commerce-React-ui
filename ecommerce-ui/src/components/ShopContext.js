import React, { createContext, useContext, useState } from "react";

const WishlistContext = createContext();

export const useWishlist = () => {
  return useContext(WishlistContext);
};

export const WishlistProvider = ({ children }) => {
  const [wishlist, setWishlist] = useState([]);
  const [cart, setCart] = useState([]);
  const [customerId, setCustomerId] = useState(null);

  const addToWishlist = (product) => {
    setWishlist((prevWishlist) => [...prevWishlist, product]);
  };

  const removeFromWishlist = (productId) => {
    setWishlist((prevWishlist) =>
      prevWishlist.filter((item) => item.id !== productId)
    );
  };

  const addToCart = (product) => {
    setCart((prevCart) => [...prevCart, product]);
  };

  const removeFromCart = (productId) => {
    setCart((prevCart) => prevCart.filter((item) => item.id !== productId));
  };

  const calculateTotal = () => {
    return cart.reduce((total, item) => total + item.price, 0).toFixed(2);
  };

  const calculateTotalQuantity = () => {
    const uniqueProductIds = new Set();
    cart.forEach((item) => {
      uniqueProductIds.add(item.id);
    });
    return uniqueProductIds.size;
  };

  const getCustomerId = () => {
    return customerId;
  }

  const setCustomerID = (customerID) => {
    setCustomerId(customerID);
  }

  return (
    <WishlistContext.Provider
      value={{
        wishlist,
        addToWishlist,
        removeFromWishlist,
        cart,
        addToCart,
        removeFromCart,
        calculateTotal,
        calculateTotalQuantity,
        getCustomerId,
        setCustomerID
      }}
    >
      {children}
    </WishlistContext.Provider>
  );
};
