import React, {useState} from 'react';
import {useWishlist} from '../ShopContext';

const CartBtn = ({product}) => {
    const [isAdded, setIsAdded] = useState(false);

    const {addToCart, removeFromCart} = useWishlist();

    const handleTCartClick = () => {
        if (!isAdded) {
            addToCart(product); 
            setIsAdded(true);
        } else {
            removeFromCart(product);
            setIsAdded(false);
        }
        
    };
    return (
        <div>
      <button
        className="btn btn-primary"
        onClick={handleTCartClick}
        disabled={isAdded}
      >
        {isAdded ? 'Remove from Cart' : 'Add to Cart'}
      </button>
    </div>
    );
}

export default CartBtn;
