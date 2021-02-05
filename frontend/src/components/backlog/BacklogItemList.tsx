import React, {useEffect} from 'react';
import {connect, ConnectedProps} from 'react-redux';
import LibraryTitle from '../LibraryTitle';
import {ListGroup} from 'react-bootstrap';
import {StoreState} from "../../store";
import {listBacklog} from "../../store/backlog";

const BacklogItemList = ({backlogItems, listBacklog}: ReduxProps) => {

    useEffect(() => {
        listBacklog();
    }, [listBacklog]);

    return (
        <ListGroup>
            {backlogItems.map(item => <LibraryTitle key={item.key} title={item.title}/>)}
        </ListGroup>
    );
};

const mapStateToProps = (state: StoreState) => ({
    backlogItems: state.backlog.data
});

const mapDispatchToProps = {
    listBacklog
};

const connector = connect(mapStateToProps, mapDispatchToProps);

type ReduxProps = ConnectedProps<typeof connector>;

export default connector(BacklogItemList);
