import {ListGroup, ListGroupItem} from "react-bootstrap";
import API from "api";

const BacklogItemList = () => {
    const {data} = API.useListBacklogQuery();

    return (
        <ListGroup>
            {data?.map(item => (<ListGroupItem key={item.id}>{item.name}</ListGroupItem>))}
        </ListGroup>
    );
};

export default BacklogItemList;
