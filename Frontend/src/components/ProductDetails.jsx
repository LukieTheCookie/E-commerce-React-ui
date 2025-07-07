import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getProductById, updateProduct } from '../service/api';
import AddToWishlistButton from './buttons/Wishlist';
import CartBtn from './buttons/CartBtn';

const ProductDetails = () => {
    const { productId } = useParams();
    const [product, setProduct] = useState({});
    const [isEditing, setIsEditing] = useState(false);
    const [editedProduct, setEditedProduct] = useState({
        name: product.name,
        description: product.description,
        price: product.price,
    });

    useEffect(() => {
        getProductById(productId)
            .then((data) => {
                setProduct(data);
                setEditedProduct(data);
            })
            .catch((error) => {
                console.error('Error Fetching product details: ', error);
            })
    }, [productId]);

    const handleEditClick = () => {
        setIsEditing(true);
    };

    const handleCancelClick = () => {
        setIsEditing(false);
        setEditedProduct({
            name: product.name,
            description: product.description,
            price: product.price,
        });
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEditedProduct({
            ...editedProduct,
            [name]: value,
        });
    };

    const handleSaveClick = async () => {
        setIsEditing(false);
        try {
            const response = await updateProduct(productId, editedProduct);
            const updatedProduct = await getProductById(productId);
            setEditedProduct(updatedProduct);
            console.log('Product updated successfully!');
        } catch (error) {
            console.error('Product update failed:', error);
        }
    }

    return (
        <div className="container mt-4">
            <h1 className="display-4">Product Details</h1>
            <div className="card">
                <div className="row">
                    <div className="col-md-4">
                        <img
                            src={process.env.PUBLIC_URL + '/logo192.png'}
                            alt="Product"
                            className="img-fluid"
                        />
                    </div>
                    <div className="col-md-8">
                        <div className="card-body">
                            {!isEditing ? (
                                <div>
                                <h2 className="card-title">{product.name}</h2>
                                <p className="card-text lead">{product.description}</p>
                                <p className="card-text text-muted">Price: R{product.price}</p>
                                <div className="row">
                                  <div className="col-md-6">
                                    <button className="btn btn-primary" onClick={handleEditClick}>
                                      Edit
                                    </button>
                                  </div>
                                  <div className="col-md-6">
                                    <div className="d-flex">
                                      <div>
                                        <AddToWishlistButton product={product} />
                                      </div>
                                      <div className="ms-2">
                                        <CartBtn product={product} />
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            ) : (
                                <div>
                                    <div className="mb-3">
                                        <label htmlFor="name" className="form-label">Name:</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            id="name"
                                            name="name"
                                            value={editedProduct.name}
                                            onChange={handleInputChange}
                                        />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="description" className="form-label">Description:</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            id="description"
                                            name="description"
                                            value={editedProduct.description}
                                            onChange={handleInputChange}
                                        />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="price" className="form-label">Price:</label>
                                        <input
                                            type="number"
                                            className="form-control"
                                            id="price"
                                            name="price"
                                            value={editedProduct.price}
                                            onChange={handleInputChange}
                                        />
                                    </div>
                                    <button className="btn btn-primary" onClick={handleSaveClick}>Save</button>
                                    <button className="btn btn-secondary ms-2" onClick={handleCancelClick}>Cancel</button>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ProductDetails;
