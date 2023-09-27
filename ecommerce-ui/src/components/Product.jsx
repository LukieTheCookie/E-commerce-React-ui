import React, {useState, useEffect} from 'react';
import { Link } from 'react-router-dom';
import { fetchProducts } from '../service/productService';
import AddToWishlistButton from './buttons/Wishlist';
import CartBtn from './buttons/CartBtn';

const Product = () => {
    const [selectedProducts, setSelectedProducts] = useState([]);
    const [products, setProducts] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [productsPerPage] = useState(9);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchProducts(setProducts, setLoading);
    }, []);

  const toggleProductSelection = (productId) => {
    if (selectedProducts.includes(productId)) {
      setSelectedProducts(selectedProducts.filter((id) => id !== productId));
    } else {
      setSelectedProducts([...selectedProducts, productId]);
    }
  };

  const handleRemoveSelected = () => {
    alert('Only admin can remove products');
  };

  const indexOfLastProduct = currentPage * productsPerPage;
  const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
  const currentProducts = products.slice(indexOfFirstProduct, indexOfLastProduct);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  return (
    <div className="container mt-4">
      <h1 className="mb-3">Products</h1>
      {selectedProducts.length > 0 && (
        <button className="btn btn-danger mb-3" onClick={handleRemoveSelected}>
          Remove Selected
        </button>
      )}
      <div className="row">
        {currentProducts.map((product) => (
          <div className="col-md-4 mb-3" key={product.id}>
            <div className="card">
              <div className="card-body">
                <div className="form-check">
                  <input
                    className="form-check-input"
                    type="checkbox"
                    checked={selectedProducts.includes(product.id)}
                    onChange={() => toggleProductSelection(product.id)}
                  />
                  <Link to={`/product/${product.id}`} className="form-check-label">
                    <h5 className="card-title mb-0">{product.name}</h5>
                  </Link>
                </div>
                <p className="card-text text-muted">Price: R{product.price}</p>
                <div className="d-flex justify-content-between">
                  <AddToWishlistButton product={product} />
                  <CartBtn product={product} />
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
      <nav>
        <ul className="pagination mt-3">
          {Array.from({ length: Math.ceil(products.length / productsPerPage) }).map((_, index) => (
            <li key={index} className={`page-item ${currentPage === index + 1 ? 'active' : ''}`}>
              <button className="page-link" onClick={() => paginate(index + 1)}>
                {index + 1}
              </button>
            </li>
          ))}
        </ul>
      </nav>
    </div>
  );
};

export default Product;
