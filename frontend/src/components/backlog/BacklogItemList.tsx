import React, {useEffect} from 'react';
import {connect, ConnectedProps} from 'react-redux';
import {ListGroup} from 'react-bootstrap';
import {StoreState} from "../../store";
import {listBacklog} from "../../store/backlog";
import BacklogItem from "./BacklogItem";

const BacklogItemList = ({backlogItems, listBacklog}: ReduxProps) => {

    useEffect(() => {
        listBacklog();
    }, [listBacklog]);

    return (
        <ListGroup>
            {backlogItems.map(item => <BacklogItem key={item.key} backlog={item}/>)}
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
