import {connect, ConnectedProps} from "react-redux";
import {StoreState} from "store";
import {actions} from "store/backlog";
import {useEffect} from "react";
import {ListGroup, ListGroupItem} from "react-bootstrap";

const mapStateToProps = (state: StoreState) => ({
    backlogItems: state.backlog.data
});
const mapDispatchToProps = {
    listBacklog: actions.list
};
const connector = connect(mapStateToProps, mapDispatchToProps);

type Props = ConnectedProps<typeof connector>;

const BacklogItemList = ({backlogItems, listBacklog}: Props) => {
    useEffect(() => {
        listBacklog();
    }, [listBacklog]);

    return (
        <ListGroup>
            {backlogItems.map(item => (<ListGroupItem key={item.id}>{item.name}</ListGroupItem>))}
        </ListGroup>
    );
};

export default connector(BacklogItemList);
