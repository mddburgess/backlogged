import React from 'react';
import {Link} from 'react-router-dom';
import {ListGroupItem} from 'react-bootstrap';
import {PencilSquare} from 'react-bootstrap-icons';
import {Title} from "../types/Title";

interface Props {
    title: Title;
}

const LibraryTitle = ({title}: Props) => (
    <ListGroupItem>
        <div className="d-flex justify-content-between">
            <h5 className="mb-0">{title.name}</h5>
            <div>
                <Link to={`/edit/${title.key}`}>
                    <PencilSquare/>
                </Link>
            </div>
        </div>
        <ul className="mb-0">
            {title.copies.map(copy => <li>{copy.platform} - {copy.service}</li>)}
        </ul>
    </ListGroupItem>
);

export default LibraryTitle;
