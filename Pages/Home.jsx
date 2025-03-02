import React from 'react';
import ProductCard from '../Components/ProductCard';
import './Home.css'; 

const products = [
    { id: 1, name: "Laptop", price: 999 },
    { id: 2, name: "Phone", price: 599 },
    { id: 3, name: "Headphones", price: 199 }
];

function Home() {
    return (
        <div className="home-container">
            <h1>Welcome to Our Store 🛍️</h1>
            <div className="products-grid">
                {products.map((product) => (
                    <ProductCard key={product.id} product={product} />
                ))}
            </div>
        </div>
    );
}

export default Home;
