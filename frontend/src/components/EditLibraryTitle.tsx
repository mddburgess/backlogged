import React from 'react';
import PropTypes from 'prop-types';
import {updateTitle} from '../store/library';
import {connect, ConnectedProps} from 'react-redux';
import {useHistory, useParams} from 'react-router-dom';
import LibraryItemForm from './LibraryTitleForm';
import {Container} from 'react-bootstrap';
import {StoreState} from "../store";

const EditLibraryItem = ({items, updateTitle}: ReduxProps) => {

    const history = useHistory();
    const params = useParams() as {
        token: string
    };

    const title = items.find(t => t.token === params.token) || {
        name: '',
        copies: []
    };

    return (
        <Container>
            <h1>Edit Library Title</h1>
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

const mapStateToProps = (state: StoreState) => ({
    items: state.library.data
});

const mapDispatchToProps = {
    updateTitle
};

const connector = connect(mapStateToProps, mapDispatchToProps);

type ReduxProps = ConnectedProps<typeof connector>;

export default connector(EditLibraryItem);
