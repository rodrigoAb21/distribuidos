demo = {
  initPickColor: function() {
    $('.pick-class-label').click(function() {
      var new_class = $(this).attr('new-class');
      var old_class = $('#display-buttons').attr('data-class');
      var display_div = $('#display-buttons');
      if (display_div.length) {
        var display_buttons = display_div.find('.btn');
        display_buttons.removeClass(old_class);
        display_buttons.addClass(new_class);
        display_div.attr('data-class', new_class);
      }
    });
  },

  initChartsPages: function() {
    chartColor = "#FFFFFF";

    ctx = document.getElementById('chartEmail').getContext("2d");

    myChart = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: [1, 2, 3],
        datasets: [{
          label: "Emails",
          pointRadius: 0,
          pointHoverRadius: 0,
          backgroundColor: [
            '#e3e3e3',
            '#4acccd',
            '#fcc468',
            '#ef8157'
          ],
          borderWidth: 0,
          data: [342, 480, 530, 120]
        }]
      },

      options: {

        legend: {
          display: false
        },

        pieceLabel: {
          render: 'percentage',
          fontColor: ['white'],
          precision: 2
        },

        tooltips: {
          enabled: false
        },

        scales: {
          yAxes: [{

            ticks: {
              display: false
            },
            gridLines: {
              drawBorder: false,
              zeroLineColor: "transparent",
              color: 'rgba(255,255,255,0.05)'
            }

          }],

          xAxes: [{
            barPercentage: 1.6,
            gridLines: {
              drawBorder: false,
              color: 'rgba(255,255,255,0.1)',
              zeroLineColor: "transparent"
            },
            ticks: {
              display: false,
            }
          }]
        },
      }
    });




      ctx1 = document.getElementById('chartEmail1').getContext("2d");

      myChart = new Chart(ctx1, {
          type: 'pie',
          data: {
              labels: [1, 2, 3],
              datasets: [{
                  label: "Emails",
                  pointRadius: 0,
                  pointHoverRadius: 0,
                  backgroundColor: [
                      '#e3e3e3',
                      '#4acccd',
                      '#fcc468',
                      '#ef8157'
                  ],
                  borderWidth: 0,
                  data: [342, 480, 530, 120]
              }]
          },

          options: {

              legend: {
                  display: false
              },

              pieceLabel: {
                  render: 'percentage',
                  fontColor: ['white'],
                  precision: 2
              },

              tooltips: {
                  enabled: false
              },

              scales: {
                  yAxes: [{

                      ticks: {
                          display: false
                      },
                      gridLines: {
                          drawBorder: false,
                          zeroLineColor: "transparent",
                          color: 'rgba(255,255,255,0.05)'
                      }

                  }],

                  xAxes: [{
                      barPercentage: 1.6,
                      gridLines: {
                          drawBorder: false,
                          color: 'rgba(255,255,255,0.1)',
                          zeroLineColor: "transparent"
                      },
                      ticks: {
                          display: false,
                      }
                  }]
              },
          }
      });






      ctx2 = document.getElementById('chartEmail2').getContext("2d");

      myChart = new Chart(ctx2, {
          type: 'pie',
          data: {
              labels: [1, 2, 3],
              datasets: [{
                  label: "Emails",
                  pointRadius: 0,
                  pointHoverRadius: 0,
                  backgroundColor: [
                      '#e3e3e3',
                      '#4acccd',
                      '#fcc468',
                      '#ef8157'
                  ],
                  borderWidth: 0,
                  data: [342, 480, 530, 120]
              }]
          },

          options: {

              legend: {
                  display: false
              },

              pieceLabel: {
                  render: 'percentage',
                  fontColor: ['white'],
                  precision: 2
              },

              tooltips: {
                  enabled: false
              },

              scales: {
                  yAxes: [{

                      ticks: {
                          display: false
                      },
                      gridLines: {
                          drawBorder: false,
                          zeroLineColor: "transparent",
                          color: 'rgba(255,255,255,0.05)'
                      }

                  }],

                  xAxes: [{
                      barPercentage: 1.6,
                      gridLines: {
                          drawBorder: false,
                          color: 'rgba(255,255,255,0.1)',
                          zeroLineColor: "transparent"
                      },
                      ticks: {
                          display: false,
                      }
                  }]
              },
          }
      });






      ctx3 = document.getElementById('chartEmail3').getContext("2d");

      myChart = new Chart(ctx3, {
          type: 'pie',
          data: {
              labels: [1, 2, 3],
              datasets: [{
                  label: "Emails",
                  pointRadius: 0,
                  pointHoverRadius: 0,
                  backgroundColor: [
                      '#e3e3e3',
                      '#4acccd',
                      '#fcc468',
                      '#ef8157'
                  ],
                  borderWidth: 0,
                  data: [342, 480, 530, 120]
              }]
          },

          options: {

              legend: {
                  display: false
              },

              pieceLabel: {
                  render: 'percentage',
                  fontColor: ['white'],
                  precision: 2
              },

              tooltips: {
                  enabled: false
              },

              scales: {
                  yAxes: [{

                      ticks: {
                          display: false
                      },
                      gridLines: {
                          drawBorder: false,
                          zeroLineColor: "transparent",
                          color: 'rgba(255,255,255,0.05)'
                      }

                  }],

                  xAxes: [{
                      barPercentage: 1.6,
                      gridLines: {
                          drawBorder: false,
                          color: 'rgba(255,255,255,0.1)',
                          zeroLineColor: "transparent"
                      },
                      ticks: {
                          display: false,
                      }
                  }]
              },
          }
      });























  }

};