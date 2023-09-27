// const BASE_URL = 'http://localhost:5000';

const makeApiRequest = async (endpoint, method, body) => {
    try {
      const response = await fetch(`${endpoint}`, {
        method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: body ? JSON.stringify(body) : null,
      });
  
      if (response.ok) {
        const responseData = await response.json();
        return responseData;
      } else {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Failed to make ' + endpoint + 'request');
      }
    } catch (error) {
      throw error;
    }
  };
  
  // Function to create a product
  const createProduct = async (product) => {
    const url = '/product';
    return makeApiRequest(url, 'POST', product);
  };
  
  // Function to remove products by IDs
  const removeProducts = async (productIds) => {
    const url = '/remove-products';
    return makeApiRequest(url, 'POST', productIds);
  };
  
  // Function to get products by IDs
  const getProducts = async (productIds) => {
    const url = '/products';
    return makeApiRequest(url, 'POST', { ids: productIds });
  };
  
  // Function to get a product by ID
  const getProductById = async (productId) => {
    const url = `/product/${productId}`;
    return makeApiRequest(url, 'GET');
  };
  
  // Function to update a product
  const updateProduct = async (productId, product) => {
    const url = `/product/${productId}`;
    return makeApiRequest(url, 'PUT', product);
  };
  
  // Function to create an order
  const createOrder = async (order) => {
    const url = '/order';
    return makeApiRequest(url, 'POST', order);
  };
  
  // Function to get orders by IDs and/or customer ID
  const getOrders = async (orderIds, customerId) => {
    const url = '/orders';
    const body = { ids: orderIds, customerId };
    return makeApiRequest(url, 'POST', body);
  };
  
  // Function to get an order by ID
  const getOrderById = async (orderId) => {
    const url = `/order/${orderId}`;
    return makeApiRequest(url, 'GET');
  };
  
  // Function to update an order
  const updateOrder = async (orderId, order) => {
    const url = `/order/${orderId}`;
    return makeApiRequest(url, 'PUT', order);
  };
  
  // Function to create a customer
  const createCustomer = async (customer) => {
    const url = '/customer';
    return makeApiRequest(url, 'POST', customer);
  };
  
  // Function to delete a customer by ID
  const deleteCustomer = async (customerId) => {
    const url = `/customer/${customerId}`;
    return makeApiRequest(url, 'DELETE');
  };
  
  export {
    createProduct,
    removeProducts,
    getProducts,
    getProductById,
    updateProduct,
    createOrder,
    getOrders,
    getOrderById,
    updateOrder,
    createCustomer,
    deleteCustomer,
  };