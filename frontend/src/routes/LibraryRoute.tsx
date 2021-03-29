import React from "react";
import LibraryItems from "../components/LibraryTitles";
import { Container } from "react-bootstrap";

const LibraryRoute = () => (
  <Container>
    <h2>Library</h2>
    <LibraryItems />
  </Container>
);

export default LibraryRoute;
