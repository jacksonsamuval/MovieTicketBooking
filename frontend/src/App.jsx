import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import Navbar from "./components/Navbar";
import BookTicket from "./components/BookTicket";

const App = () => {
  return (
    <>
    <Navbar/>
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register/>}/>
      <Route path="/" element={<Home/>}/>
      <Route path="/book/:showId" element={<BookTicket/>}/>
    </Routes>
    </>
  );
};

export default App;
