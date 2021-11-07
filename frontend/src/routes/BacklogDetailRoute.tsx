import {useParams} from "react-router-dom";
import API from "api";
import {Badge, Container, ListGroup, Row} from "react-bootstrap";

interface Params {
    id: string;
}

const BacklogDetailRoute = () => {
    const params = useParams<Params>();
    const {data} = API.useRetrieveBacklogItemQuery(Number(params.id));

    return (
        <Container>
            <Container className="rounded border py-2 mb-3">
                <h2 className="mb-0">{data?.name}</h2>
                <Badge>{data?.status}</Badge>
            </Container>
            <Row><h6>Activities</h6></Row>
            <ListGroup>
                {data?.activities?.map(a =>
                    <ListGroup.Item>
                        {a.type} {a.duration} {a.fromStatus} {a.toStatus}
                    </ListGroup.Item>
                )}
            </ListGroup>
        </Container>
    );
}

export default BacklogDetailRoute;
