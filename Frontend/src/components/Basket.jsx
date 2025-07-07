import React from 'react';
import { useWishlist } from './ShopContext';
import CartBtn from './buttons/CartBtn';
import { NavLink } from 'react-router-dom';

const Basket = () => {
  const { wishlist, removeFromWishlist } = useWishlist();

  return (
    <div className="container mt-4">
      <h1 className="display-4">Basket Page</h1>
      {wishlist.length === 0 ? (
        <p>Your wishlist is empty.</p>
      ) : (
        <div>
          {wishlist.map((item) => (
            <div className="card mb-3" key={item.id}>
              <div className="card-body">
                <h5 className="card-title">{item.name}</h5>
                <p className='card-text lead'>{item.description}</p>
                <p className="card-text text-muted">Price: R{item.price}</p>
                <div className="d-flex justify-content-between">
                  <CartBtn product={item} />
                  <button
                    className="btn btn-danger"
                    onClick={() => removeFromWishlist(item.id)}
                  >
                    Remove
                  </button>
                </div>
              </div>
            </div>
          ))}
          <div className="d-flex justify-content-end">
            <NavLink to="/cart" className="btn btn-primary">
              Go to Cart
            </NavLink>
          </div>
        </div>
      )}
    </div>
  );
};

export default Basket;