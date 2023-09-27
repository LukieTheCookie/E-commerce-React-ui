import React, { useState } from 'react';
import {useWishlist} from '../ShopContext';

const AddToWishlistButton = ({ product }) => {
  const [isAdded, setIsAdded] = useState(false);

    const {addToWishlist, removeFromWishlist} = useWishlist();
    const handleAddToWishlistClick = () => {
        if (!isAdded) {
            addToWishlist(product); 
            setIsAdded(true);
        } else {
            removeFromWishlist(product);
            setIsAdded(false);
        }
        
    };

  return (
    <div>
      <button
        className="btn btn-secondary"
        onClick={handleAddToWishlistClick}
        disabled={isAdded}
      >
        {isAdded ? 'Remove from Wishlist' : 'Add to Wish-list'}
      </button>
    </div>
  );
};

export default AddToWishlistButton;