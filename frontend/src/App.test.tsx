import {render, screen} from '@testing-library/react';
import App from './App';

test('renders header', () => {
  render(<App/>);
  const homeElement = screen.getByText(/Backlogged/i);
  expect(homeElement).toBeInTheDocument();
});
