import React, {useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import LibraryItem from './LibraryItem';
import {deleteTitle, listTitles} from '../store/library';
import {Link} from 'react-router-dom';
import {ListGroup, ListGroupItem} from 'react-bootstrap';

const LibraryItems = ({items, listTitles, deleteTitle}) => {

    useEffect(() => {
        listTitles();
    }, [listTitles]);

    return (
        <ListGroup>
            {items.map(item => <LibraryItem key={item.token} item={item} deleteTitle={deleteTitle}/>)}
            <ListGroupItem>
                <Link to="/new">
                    Add item
                </Link>
            </ListGroupItem>
        </ListGroup>
    );
};

LibraryItems.propTypes = {
    items: PropTypes.arrayOf(PropTypes.object.isRequired).isRequired,
    listTitles: PropTypes.func.isRequired
};

const mapStateToProps = (state) => ({
    items: state.library.data
});

const mapDispatchToProps = {
    listTitles,
    deleteTitle
};

export default connect(mapStateToProps, mapDispatchToProps)(LibraryItems);
