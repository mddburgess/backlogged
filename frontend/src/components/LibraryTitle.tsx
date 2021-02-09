import React from 'react';
import {Link} from 'react-router-dom';
import {ListGroupItem} from 'react-bootstrap';
import {JournalPlus} from 'react-bootstrap-icons';
import {Title} from "../types/Title";

interface Props {
    title: Title;
}

const LibraryTitle = ({title}: Props) => (
    <ListGroupItem className="px-2 py-1">
        <div className="d-flex justify-content-between">
            <Link to={`/edit/${title.key}`}>
                {title.name}
            </Link>
            <div>
                <JournalPlus/>
            </div>
        </div>
        <ul className="mb-0">
            {title.copies.map(copy => <small>
                <li>{copy.platform} - {copy.service}</li>
            </small>)}
        </ul>
    </ListGroupItem>
);

export default LibraryTitle;
