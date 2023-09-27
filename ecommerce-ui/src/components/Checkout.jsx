import React, { useState } from 'react';
import { useWishlist } from './ShopContext';
import { createOrder } from '../service/api';
import { NavLink } from 'react-router-dom';

const Checkout = () => {
    const { cart, removeFromCart, calculateTotal, getCustomerId } = useWishlist();
    const [isOrderPlaced, setIsOrderPlaced] = useState(false);
    const handlePlaceOrderClick = async () => {

        const productIds = cart.map((item) => item.id);
        const total = parseFloat(calculateTotal());
        if (getCustomerId() === null) {
            alert('Please register before placing an order!');
            return;
        }
        const requestBody = {
            customer_id: getCustomerId(),
            paid: false,
            products: productIds,
            total: total
        }; 
        
        try {
            const response = await createOrder(requestBody);
            if (response.id) {
                setIsOrderPlaced(true);
            } else {
                console.error('Order failed:');
            }
        } catch (error) {
            console.error('Order placement failed:', error);
        }
    };
    return (
        <div className="container mt-4">
            <h1 className="display-4">Shopping Cart</h1>
            {cart.length === 0 ? (
                <>
                    <p>Your Cart is empty.</p>
                    <NavLink to="/past-orders" className="btn btn-outline-dark">
                        Past Orders
                    </NavLink>
                </>
            ) : (
                <div>
                    {cart.map((item) => (
                        <div className="card mb-3" key={item.id}>
                            <div className="card-body">
                                <h5 className="card-title">{item.name}</h5>
                                <p className='card-text lead'>{item.description}</p>
                                <p className="card-text text-muted">Price: R{item.price}</p>
                                <button className="btn btn-danger" onClick={() => removeFromCart(item.id)}>
                                    Remove
                                </button>
                            </div>
                        </div>
                    ))}
                    <div className='mt-3'>
                        <h4 className="text-primary">
                            Grand Total: R{calculateTotal()}
                        </h4>
                        <button
                            className="btn btn-primary"
                            onClick={handlePlaceOrderClick}
                        >
                            Place Order
                        </button>
                        {isOrderPlaced && (
                            <p className="mt-3 alert alert-success">Order has been Placed! Payment details will be sent shortly</p>
                        )}
                    </div>
                </div>
            )}
        </div>
    );
}

export default Checkout;
