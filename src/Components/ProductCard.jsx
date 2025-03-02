import React, { useContext } from 'react';
import { CartContext } from '../Context/CartContext';
import './ProductCard.css'; 

function ProductCard({ product }) {
    const { addToCart } = useContext(CartContext);

    return (
        <div className="product-card">
            <h3>{product.name}</h3>
            <p>Price: ${product.price}</p>
            <button onClick={() => addToCart(product)}>Add to Cart</button>
        </div>
    );
}

export default ProductCard;
