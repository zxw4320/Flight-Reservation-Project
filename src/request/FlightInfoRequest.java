package request;

import request.FlightOrders.FlightOrder;

public class FlightInfoRequest implements Request{

  /**
   * The order flights will be returned in.
   */
  private FlightOrder sortOrder;

  public FlightInfoRequest(FlightOrder sortOrder){
    this.sortOrder = sortOrder;
  }

  @Override
  public void execute() {

  }
}
