import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router";
// import App from "./app";
import Book from "./Book.jsx";
import App from "./App.jsx";
import UserDataFetcher from "./UserDataFetcher.jsx";

const root = document.getElementById("root");

ReactDOM.createRoot(root).render(
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<App />} />
            <Route path="/book" element={<Book />} />
            <Route path="/user" element={<UserDataFetcher />} />
        </Routes>
    </BrowserRouter>
);