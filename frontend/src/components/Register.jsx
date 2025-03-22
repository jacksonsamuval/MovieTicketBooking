import axios from 'axios';
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link, redirect, useNavigate } from 'react-router-dom';

const Register = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleRegister = async () => {
        try {
            const response = await axios.post("http://localhost:8090/api/register", { username, password });
            if (response.status === 200) {
                localStorage.setItem("token", response.data);
                console.log("Registered Successfully");
                alert("Registered Successfully");
                setUsername("");
                setPassword("");
                navigate("/");
            }
        } catch (error) {
            if (error.response) {
                if (error.response.status === 402) {
                    alert("Username Already Exists");
                } else {
                    alert("Server Error: " + error.response.data.message);
                }
            } else {
                console.log("Request Failed", error);
                alert("Request Failed: Check your network or server.");
            }
            setPassword("");
        }
    };

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="card p-4 shadow-lg" style={{ width: '350px' }}>
                <h3 className="text-center mb-4">Register</h3>
                <div className="mb-3">
                    <label className="form-label">Username</label>
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Enter username"
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input
                        type="password"
                        className="form-control"
                        placeholder="Enter password"
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button className="btn btn-danger w-100" onClick={handleRegister}>Register</button>
                <div className="text-center mt-3">
                    <h7>Have Account?</h7><h7><Link to="/login" style={{ color: "red", fontWeight: "bold" }}>Login Here</Link></h7>
                </div>
            </div>
        </div>
    );
};

export default Register;
