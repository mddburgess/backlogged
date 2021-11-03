import {useParams} from "react-router-dom";
import API from "api";

interface Params {
    id: string;
}

const BacklogDetailRoute = () => {
    const params = useParams<Params>();
    const {data} = API.useRetrieveBacklogItemQuery(Number(params.id));

    return (
        <pre>
            {JSON.stringify(data, undefined, 2)}
        </pre>
    );
}

export default BacklogDetailRoute;
