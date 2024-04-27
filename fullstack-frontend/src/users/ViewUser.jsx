import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewUser() {
  const { id } = useParams();
  const [user, setUser] = useState({
    username: "",
    phone: "",
    email: "",
  });
  useEffect(() => {
    loadUser();
  }, []);

  const loadUser = async () => {
    const result = await axios.get(`http://localhost:6680/users/${id}`);
    setUser(result.data);
    // console.log(result.data);
  };
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4  ">User Details</h2>
          <div className="card">
            <div className="card-header">
              <center>
                <h4>Details of user id- {user.id}</h4>
              </center>

              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Name: </b>
                  {user.username}
                </li>
                <li className="list-group-item">
                  <b>Phone: </b>
                  {user.phone}
                </li>
                <li className="list-group-item">
                  <b>Email: </b>
                  {user.email}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to="/">
            Back To Home
          </Link>
        </div>
      </div>
    </div>
  );
}
