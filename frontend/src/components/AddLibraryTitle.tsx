import React from 'react';
import {addTitle} from '../store/library';
import {connect, ConnectedProps} from 'react-redux';
import {useHistory} from 'react-router-dom';
import LibraryTitleForm from './LibraryTitleForm';
import {Container} from 'react-bootstrap';

const AddLibraryTitle = ({addTitle}: ReduxProps) => {

    const history = useHistory();

    return (
        <Container>
            <h1>Add Library Title</h1>
            <LibraryTitleForm
                initialValues={{
                    name: '',
                    copies: [
                        {
                            platform: '',
                            service: ''
                        }
                    ]
                }}
                onSubmit={title => {
                    addTitle(title);
                    history.push('/');
                }}
            />
        </Container>
    );
};

const mapDispatchToProps = {
    addTitle
};

const connector = connect(undefined, mapDispatchToProps);

type ReduxProps = ConnectedProps<typeof connector>;

export default connector(AddLibraryTitle);
