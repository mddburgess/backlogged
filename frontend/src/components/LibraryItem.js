import React from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';
import {ListGroupItem} from 'react-bootstrap';
import {PencilSquare} from 'react-bootstrap-icons';

const LibraryItem = (props) => (
    <ListGroupItem>
        <div className="d-flex justify-content-between">
            <h5 className="mb-0">{props.item.title}</h5>
            <div>
                <Link to={`/edit/${props.item.token}`}>
                    <PencilSquare/>
                </Link>
            </div>
        </div>
        <ul className="mb-0">
            {props.item.copies.map(copy => <li>{copy.platform} - {copy.service}</li>)}
        </ul>
    </ListGroupItem>
);

LibraryItem.propTypes = {
    item: PropTypes.object.isRequired
};

export default LibraryItem;
