import React from "react";
import { Nav, Navbar } from "react-bootstrap";
import { NavLink } from "react-router-dom";

const Header = () => (
  <Navbar sticky="top" bg="dark" variant="dark">
    <Navbar.Brand>Backlogged</Navbar.Brand>
    <Nav variant="pills">
      <Nav.Link as={NavLink} exact to="/">
        Home
      </Nav.Link>
      <Nav.Link as={NavLink} to="/library">
        Library
      </Nav.Link>
      <Nav.Link as={NavLink} to="/backlog">
        Backlog
      </Nav.Link>
    </Nav>
  </Navbar>
);

export default Header;
