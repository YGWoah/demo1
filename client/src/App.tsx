import { useState, useEffect } from 'react';
import './App.css';

// Define the type for an order to match the backend model
interface Order {
  id: string; // UUID is a string
  product: string;
  quantity: number;
}

function App() {
  const [orders, setOrders] = useState<Order[]>([]);
  const [product, setProduct] = useState('');
  const [quantity, setQuantity] = useState(1);
  const [error, setError] = useState<string | null>(null);

  // Fetch all orders when the component mounts
  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const response = await fetch('/orders');
      if (!response.ok) {
        throw new Error('Failed to fetch orders');
      }
      const data: Order[] = await response.json();
      setOrders(data);
      setError(null);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An unknown error occurred');
    }
  };

  const handleCreateOrder = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await fetch(`/orders?product=${encodeURIComponent(product)}&quantity=${quantity}`, {
        method: 'POST',
      });
      if (!response.ok) {
        throw new Error('Failed to create order');
      }
      // Reset form and refresh list
      setProduct('');
      setQuantity(1);
      fetchOrders();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An unknown error occurred');
    }
  };

  const handleUpdateOrder = async (id: string, newQuantity: number) => {
    if (newQuantity <= 0) {
      setError('Quantity must be positive.');
      return;
    }
    try {
      const response = await fetch(`/orders/${id}?quantity=${newQuantity}`, {
        method: 'PUT',
      });
       if (!response.ok) {
        throw new Error('Failed to update order');
      }
      fetchOrders(); // Refresh list
    } catch (err) {
       setError(err instanceof Error ? err.message : 'An unknown error occurred');
    }
  };

  const handleDeleteOrder = async (id: string) => {
    try {
      const response = await fetch(`/orders/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to delete order');
      }
      fetchOrders(); // Refresh list
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An unknown error occurred');
    }
  };

  return (
    <div className="App">
      <h1>Order Management</h1>

      {error && <p className="error">{error}</p>}

      <form onSubmit={handleCreateOrder} className="order-form">
        <h2>Create New Order</h2>
        <input
          type="text"
          placeholder="Product Name"
          value={product}
          onChange={(e) => setProduct(e.target.value)}
          required
        />
        <input
          type="number"
          min="1"
          placeholder="Quantity"
          value={quantity}
          onChange={(e) => setQuantity(parseInt(e.target.value, 10))}
          required
        />
        <button type="submit">Create Order</button>
      </form>

      <div className="order-list">
        <h2>Existing Orders</h2>
        {orders.length === 0 ? (
          <p>No orders found.</p>
        ) : (
          orders.map((order) => (
            <div key={order.id} className="order-item">
              <p><strong>Product:</strong> {order.product}</p>
              <p><strong>Quantity:</strong> {order.quantity}</p>
              <p><small>ID: {order.id}</small></p>
              <div className="order-actions">
                 <button onClick={() => handleUpdateOrder(order.id, order.quantity + 1)}>Qty +</button>
                 <button onClick={() => handleUpdateOrder(order.id, order.quantity - 1)}>Qty -</button>
                 <button className="delete-btn" onClick={() => handleDeleteOrder(order.id)}>Delete</button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default App;