import React from 'react';
import PropTypes from 'prop-types';
import {addTitle} from '../store/library';
import {connect} from 'react-redux';
import {useHistory} from 'react-router-dom';
import LibraryItemForm from './LibraryTitleForm';
import {Container} from 'react-bootstrap';

const AddLibraryItem = ({addTitle}) => {

    const history = useHistory();

    return (
        <Container>
            <h1>Add Library Item</h1>
            <LibraryItemForm
                initialValues={{
                    title: '',
                    copies: [
                        {
                            platform: '',
                            service: ''
                        }
                    ]
                }}
                onSubmit={(values) => {
                    addTitle(values);
                    history.push('/');
                }}
            />
        </Container>
    );
};

AddLibraryItem.propTypes = {
    addTitle: PropTypes.func.isRequired
};

const mapDispatchToProps = {
    addTitle
};

export default connect(undefined, mapDispatchToProps)(AddLibraryItem);
