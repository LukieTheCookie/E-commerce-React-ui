import {createProduct, getProducts, getProductById, removeProducts} from './api'

export const fetchProducts = (setProducts, setLoading) => {
    setLoading(true);
    getProducts()
      .then((data) => {
        setProducts(data);
      })
      .catch((error) => {
        console.error('Error Fetching products: ', error);
      })
      .finally(() => {
        setLoading(false);
      });
  };

export const handleRemove = (selectedProductIds, setSelectedProductIds) => {
    const selectedIdObject = {ids: selectedProductIds}
    removeProducts(selectedIdObject)
        .then((response) => {
            if (response.status === 200) {
                setSelectedProductIds([]);
                fetchProducts(); 
            } else {
                console.error('Error removing products:', response.statusText);
            }
        })
        .catch((error) => {
            console.error('Error removing products:', error);
        });
};