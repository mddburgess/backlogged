import { Route, Switch } from "react-router-dom";
import ActivityLogRoute from "./ActivityLogRoute";
import LibraryRoute from "./LibraryRoute";
import AddLibraryItem from "../components/AddLibraryTitle";
import EditLibraryItem from "../components/EditLibraryTitle";
import BacklogRoute from "./BacklogRoute";

const Routes = () => (
  <Switch>
    <Route exact path="/" component={ActivityLogRoute} />
    <Route path="/backlog" component={BacklogRoute} />
    <Route path="/library" component={LibraryRoute} />
    <Route path="/new" component={AddLibraryItem} />
    <Route path="/edit/:key" component={EditLibraryItem} />
  </Switch>
);

export default Routes;
