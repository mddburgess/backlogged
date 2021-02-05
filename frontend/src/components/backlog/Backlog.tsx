import React from "react";
import {Container} from "react-bootstrap";
import BacklogItemList from "./BacklogItemList";

const Backlog = () => (
    <Container>
        <h2>Backlog</h2>
        <BacklogItemList/>
    </Container>
)

export default Backlog;
