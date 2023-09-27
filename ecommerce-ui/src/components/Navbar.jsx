import React from 'react';
import { NavLink } from 'react-router-dom';
import { useWishlist } from './ShopContext';

function Navbar() {
    const {calculateTotalQuantity} = useWishlist();

        return (
            <>
            <nav className="navbar navbar-expand-lg bg-body-tertiary">
                <div className="container">
                    <a className="navbar-brand fw-bold fs-4" href="#">
                        TAKE-A-LITTLE
                        </a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mx-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <NavLink to="/" className="nav-link active" aria-current="page" href="#">
                                    Products
                                </NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink to="/basket" className="nav-link" href="#">
                                    Wishlist
                                </NavLink>
                            </li>
                        </ul>
                        <div className="buttons">
                                <NavLink to="/register" className="btn btn-outline-dark ms-2">
                                    <i className="fa fa-user-plus me-1"></i>Register
                                </NavLink>
                                <NavLink to="/cart" className="btn btn-outline-dark ms-2">
                                    <i className="fa fa-shopping-cart me-1"></i>Cart ({calculateTotalQuantity()})
                                </NavLink>
                            </div>
                    </div>
                </div>
            </nav>
            </>
        );
}


export default Navbar;