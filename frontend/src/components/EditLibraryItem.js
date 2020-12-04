import React from 'react';
import PropTypes from 'prop-types';
import {updateTitle} from '../store/library';
import {connect} from 'react-redux';
import {useHistory, useParams} from 'react-router-dom';
import LibraryItemForm from './LibraryItemForm';
import {Container} from 'react-bootstrap';

const EditLibraryItem = ({items, updateTitle}) => {

    const history = useHistory();
    const params = useParams();
    const title = items.find(t => t.token === params.token);

    return (
        <Container>
            <h1>Edit Library Item</h1>
            <LibraryItemForm
                initialValues={title}
                onSubmit={(values) => {
                    updateTitle(values);
                    history.push('/');
                }}
            />
        </Container>
    );
};

EditLibraryItem.propTypes = {
    updateTitle: PropTypes.func.isRequired
};

const mapStateToProps = (state) => ({
    items: state.library.data
});

const mapDispatchToProps = {
    updateTitle
};

export default connect(mapStateToProps, mapDispatchToProps)(EditLibraryItem);
