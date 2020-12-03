import React from 'react';
import LibraryItems from './LibraryItems';
import {Container} from 'react-bootstrap';

const Library = () => (
    <Container className="container-lg">
        <h2>Library</h2>
        <LibraryItems/>
    </Container>
);

export default Library;
